package customer.engagement.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.social.facebook.api.Account;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.GroupMembership;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.Invitation;
import org.springframework.social.facebook.api.Location;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PlaceTag;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.WorkEntry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import customer.engagement.dao.CorporationRepository;
import customer.engagement.dao.CustomerRepository;
import customer.engagement.dao.PageRepository;
import customer.engagement.dao.UserRepository;
import customer.engagement.domain.EducationDetails;
import customer.engagement.domain.Event;
import customer.engagement.domain.LikePages;
import customer.engagement.domain.TaggedPlaces;
import customer.engagement.domain.UserGroup;
import customer.engagement.domain.UserPage;
import customer.engagement.domain.WorkDetail;
import customer.engagement.dto.LoginDTO;
import customer.engagement.dto.Customer;
import customer.engagement.dto.Corporation;

@Service
@Transactional
public class FacebookServiceImpl implements FacebookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepositroy;

	@Autowired
	private CorporationRepository corporationRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private JavaMailSender sender;

	@Override
	public LoginDTO login(Facebook facebook) {

		boolean isCustomer = true;

		if (facebook == null) {
			// TODO
		}

		LoginDTO loginDTO = new LoginDTO();

		User userProfile = facebook.userOperations().getUserProfile();
		loginDTO.setName(userProfile.getName());
		loginDTO.setEmail(userProfile.getEmail());
		if (userProfile.getCover() != null) {
			loginDTO.setCoverPicsURL(userProfile.getCover().getSource());
		}

		customer.engagement.domain.User user = new customer.engagement.domain.User();
		displayUserProfile(userProfile, user);

		PagedList<Account> accounts = facebook.pageOperations().getAccounts();
		if (accounts.size() == 1) {
			isCustomer = false;
		}
		getAllPages(accounts, user);

		// List of all groups created by users..
		List<UserGroup> userGroups = new ArrayList<>();
		PagedList<GroupMembership> groups = facebook.groupOperations().getMemberships();
		for (GroupMembership membership : groups) {
			UserGroup group = new UserGroup();
			group.setName(membership.getName());
			group.setId(membership.getId());
			userGroups.add(group);
		}
		user.setUserGroup(userGroups);

		PagedList<Invitation> invitations = facebook.eventOperations().getAttending();
		getAllInvitations(invitations, user);

		List<PlaceTag> placeTags = facebook.userOperations().getTaggedPlaces();
		getAllTaggedPlaces(placeTags, user);

		// byte[] image = facebook.userOperations().getUserProfileImage();
		byte[] image = facebook.userOperations().getUserProfileImage(ImageType.LARGE);
		String encodedBase64Image = Base64.encodeBase64String(image);
		loginDTO.setProfilePics("data:image/jpg;base64, " + encodedBase64Image);
		user.setProfilePics("data:image/jpg;base64, " + encodedBase64Image);

		PagedList<Page> likes = facebook.likeOperations().getPagesLiked();
		getAllLikes(likes, user);

		// TODO can be used to find all tagged places,status
		PagedList<Post> tagged = facebook.feedOperations().getTagged();

		customer.engagement.domain.User dbUser = userRepository.findByFacebookId(user.getFacebookId());
		if (dbUser == null) {
			userRepository.save(user);
			if (isCustomer) {
				Customer customer = getCustomer(user);
				List<String> pageIds = new ArrayList<>();
				for (Event e : user.getEvent()) {
					if (pageRepository.findOne(e.getId()) == null) {
						customer.engagement.dto.Page page = new customer.engagement.dto.Page();
						page.setId(e.getId());
						page.setName(e.getEventName());
						page.setData(e.getDescription());
						pageRepository.save(page);
					}
					pageIds.add(e.getId());
				}
				for (LikePages likePages : user.getLikes()) {
					if (pageRepository.findOne(likePages.getId()) == null) {
						customer.engagement.dto.Page page = new customer.engagement.dto.Page();
						page.setId(likePages.getId());
						page.setName(likePages.getName());
						page.setCategory(likePages.getCategory());
						page.setData(likePages.getDescription());
						pageRepository.save(page);
					}
					pageIds.add(likePages.getId());
				}

				for (TaggedPlaces places : user.getTaggedPlaces()) {
					if (pageRepository.findOne(places.getId()) == null) {
						customer.engagement.dto.Page page = new customer.engagement.dto.Page();
						page.setId(places.getId());
						page.setName(places.getName());
						page.setCategory(places.getCategory());
						page.setData(places.getDescription());
						pageRepository.save(page);
					}
					pageIds.add(places.getId());
				}
				customer.setPages(pageIds);
				customerRepositroy.save(customer);
				try {
					if(user.getEmail() != null && !user.getEmail().isEmpty())
						sendEmail(user);
				} catch (Exception e) {
					LOGGER.error("Something went wrong while sending email for user: {}", user.getName(),
							e.getMessage());
				}
			} else {
				corporationRepository.save(getCorporation(user));
			}

		}
		return loginDTO;
	}

	private Customer getCustomer(customer.engagement.domain.User user) {
		Customer customer = new Customer();
		customer.setId(user.getFacebookId());
		customer.setCity(user.getCurrentLocation());
		customer.setGender(user.getGender());
		customer.setName(user.getName());
		customer.setReligion(user.getReligion());
		return customer;
	}

	private Corporation getCorporation(customer.engagement.domain.User user) {
		Corporation corporation = new Corporation();
		corporation.setCorporationId(user.getFacebookId());
		corporation.setCorporationName(user.getUserPage().get(0).getName());
		corporation.setURL(user.getWebsite());
		corporation.setAddress(user.getCurrentLocation());
		corporation.setFunctionalDomain(user.getUserPage().get(0).getCategory());
		return corporation;
	}

	/**
	 * Change email into HTML format
	 * 
	 * @param user
	 * @throws Exception
	 */

	private void sendEmail(customer.engagement.domain.User user) throws Exception {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(user.getEmail());
			messageHelper.setSubject("Welcome to Travel card");
			messageHelper.setText("Hi " + user.getName() + " , Thank you for signing...");
		};
		try {
			sender.send(messagePreparator);
		} catch (MailException e) {
			LOGGER.error("Error while sending EMAIL: " + e.getMessage());
			throw new Exception("Error while sending email for user: " + user.getName(), e);
		}
	}

	private void getAllTaggedPlaces(List<PlaceTag> placeTags, customer.engagement.domain.User user) {
		List<TaggedPlaces> taggedPlaces = new ArrayList<>();
		for (PlaceTag placeTag : placeTags) {
			TaggedPlaces places = new TaggedPlaces();
			Page page = placeTag.getPlace();
			places.setCategory(page.getCategory());
			places.setDescription(page.getDescription());

			places.setId(page.getId());
			places.setName(page.getName());
			places.setWebsite(page.getWebsite());
			Location location = page.getLocation();
			if (location != null) {
				customer.engagement.domain.Location loc = new customer.engagement.domain.Location();
				loc.setCity(location.getCity());
				loc.setCountry(location.getCountry());
				loc.setStreet(location.getStreet());
				loc.setZip(location.getZip());
				places.setLocation(loc);
			}
			taggedPlaces.add(places);
		}
		user.setTaggedPlaces(taggedPlaces);
	}

	@SuppressWarnings("unchecked")
	private void getAllInvitations(PagedList<Invitation> invitations, customer.engagement.domain.User user) {
		List<Event> events = new ArrayList<>();
		for (Invitation invitation : invitations) {
			Event event = new Event();
			event.setId(invitation.getEventId());
			event.setEventName(invitation.getName());
			Map<String, Object> extraData = invitation.getExtraData();
			if (extraData != null) {
				event.setDescription((String) extraData.get("description"));
				LinkedHashMap<String, Object> place = (LinkedHashMap<String, Object>) extraData.get("place");
				if (place != null) {
					event.setEventName((String) place.get("name"));
					LinkedHashMap<String, Object> location = (LinkedHashMap<String, Object>) place.get("location");
					if (location != null) {
						customer.engagement.domain.Location location2 = new customer.engagement.domain.Location();
						location2.setCity((String) location.get("city"));
						location2.setCountry((String) location.get("country"));
						location2.setStreet((String) location.get("street"));
						location2.setZip((String) location.get("zip"));
						event.setLocation(location2);
					}
				}
			}
			events.add(event);
		}
		user.setEvent(events);
	}

	private void getAllLikes(PagedList<Page> likes, customer.engagement.domain.User user) {
		List<LikePages> likePages = new ArrayList<>();
		for (Page like : likes) {
			LikePages pages = new LikePages();
			pages.setCategory(like.getCategory());
			pages.setDescription(like.getDescription());
			pages.setId(like.getId());
			pages.setName(like.getName());
			pages.setWebsite(like.getWebsite());
			Location location = like.getLocation();
			if (location != null) {
				customer.engagement.domain.Location location2 = new customer.engagement.domain.Location();
				location2.setCity(location.getCity());
				location2.setCountry(location.getCountry());
				location2.setStreet(location.getStreet());
				location2.setZip(location.getZip());
				pages.setLocation(location2);
			}
			likePages.add(pages);
		}
		user.setLikes(likePages);
	}

	private void displayUserProfile(User userProfile, customer.engagement.domain.User user) {

		user.setFacebookId(userProfile.getId());
		user.setEmail(userProfile.getEmail());
		user.setName(userProfile.getName());

		user.setGender(userProfile.getGender());

		user.setBirthday(userProfile.getBirthday());
		if (userProfile.getHometown() != null) {
			user.setHomeTownLocation(userProfile.getHometown().getName());
		}

		if (userProfile.getLocation() != null) {
			user.setCurrentLocation(userProfile.getLocation().getName());
		}

		if (userProfile.getCover() != null) {
			user.setCoverPicsURL(userProfile.getCover().getSource());
		}

		user.setWebsite(userProfile.getWebsite());

		List<WorkDetail> workDetails = new ArrayList<>();
		if (userProfile.getWork() != null) {
			for (WorkEntry work : userProfile.getWork()) {
				WorkDetail workDetail = new WorkDetail();
				workDetail.setEmployerName(work.getEmployer().getName());
				workDetails.add(workDetail);
			}
		}
		user.setWorkDetails(workDetails);

		List<EducationDetails> educationDetails = new ArrayList<>();
		if (userProfile.getEducation() != null) {
			for (EducationExperience exp : userProfile.getEducation()) {
				EducationDetails details = new EducationDetails();
				details.setInstituteName(exp.getSchool().getName());
				details.setType(exp.getType());
				educationDetails.add(details);
			}
		}
		user.setEducationDetails(educationDetails);
		LOGGER.debug("UserProfile : {}", user);

	}

	/**
	 * Will display list of all user's page
	 * 
	 * @param accounts
	 *            list of all page accounts
	 */
	private void getAllPages(PagedList<Account> accounts, customer.engagement.domain.User user) {
		List<UserPage> pages = new ArrayList<>();
		for (Account account : accounts) {
			UserPage userPage = new UserPage();
			userPage.setName(account.getName());
			userPage.setId(account.getId());
			userPage.setCategory(account.getCategory());
			pages.add(userPage);
		}
		LOGGER.debug("Get all pages: {}", pages);
		user.setUserPage(pages);
	}
}
