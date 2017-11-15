package customer.engagement.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.facebook.api.Account;
import org.springframework.social.facebook.api.Achievement;
import org.springframework.social.facebook.api.AchievementOperations;
import org.springframework.social.facebook.api.Album;
import org.springframework.social.facebook.api.BookActions;
import org.springframework.social.facebook.api.CommentOperations;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.EventOperations;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FamilyMember;
import org.springframework.social.facebook.api.FitnessActions;
import org.springframework.social.facebook.api.FriendList;
import org.springframework.social.facebook.api.FriendOperations;
import org.springframework.social.facebook.api.GeneralActions;
import org.springframework.social.facebook.api.GroupMembership;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.Invitation;
import org.springframework.social.facebook.api.LikeOperations;
import org.springframework.social.facebook.api.Location;
import org.springframework.social.facebook.api.MediaOperations;
import org.springframework.social.facebook.api.MusicActions;
import org.springframework.social.facebook.api.OpenGraphOperations;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PlaceTag;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.UserIdForApp;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.UserTaggableFriend;
import org.springframework.social.facebook.api.Video;
import org.springframework.social.facebook.api.VideoActions;
import org.springframework.social.facebook.api.WorkEntry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import customer.engagement.domain.EducationDetails;
import customer.engagement.domain.UserPage;
import customer.engagement.domain.WorkDetail;
import customer.engagement.dto.LoginDTO;

@Service
public class FacebookServiceImpl implements FacebookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookServiceImpl.class);

	@Override
	public LoginDTO login(Facebook facebook) {

		if (facebook == null) {
			// TODO
		}

		LoginDTO loginDTO = new LoginDTO();

		// User user = facebook.fetchObject("me", User.class, FACEBOOK_FILEDS);
		User userProfile = facebook.userOperations().getUserProfile();
		loginDTO.setName(userProfile.getName());
		loginDTO.setEmail(userProfile.getEmail());
		loginDTO.setCoverPicsURL(userProfile.getCover().getSource());
		customer.engagement.domain.User user = new customer.engagement.domain.User();
		displayUserProfile(userProfile, user);

		PagedList<Account> accounts = facebook.pageOperations().getAccounts();
		Page page = facebook.pageOperations().getPage("930757893637831");
		getAllPages(accounts, user);

		// List of all groups created by users..
		PagedList<GroupMembership> groups = facebook.groupOperations().getMemberships();
		for (GroupMembership membership : groups) {
			LOGGER.info("Group Name: " + membership.getName());
			LOGGER.info("Group Id: " + membership.getName());
		}

		PagedList<Invitation> invitations = facebook.eventOperations().getAttending();
		getAllInvitations(invitations);

		boolean isAuthorized = facebook.isAuthorized();

		List<PlaceTag> placeTags = facebook.userOperations().getTaggedPlaces();
		getAllTaggedPlaces(placeTags);

		// byte[] image = facebook.userOperations().getUserProfileImage();
		byte[] image = facebook.userOperations().getUserProfileImage(ImageType.LARGE);
		String encodedBase64Image = Base64.encodeBase64String(image);
		loginDTO.setProfilePics("data:image/jpg;base64, " + encodedBase64Image);

		PagedList<Page> likes = facebook.likeOperations().getPagesLiked();
		getAllLikes(likes);
		System.out.println("User: " + user);

		return loginDTO;
	}

	private void getAllTaggedPlaces(List<PlaceTag> placeTags) {
		for (PlaceTag placeTag : placeTags) {
			Page page = placeTag.getPlace();
			LOGGER.info("Category: " + page.getCategory());
			LOGGER.info("Description:" + page.getDescription());
			LOGGER.info("Fan count: " + page.getFanCount());
			LOGGER.info("PageId: " + page.getId());
			LOGGER.info("Name: " + page.getName());
			LOGGER.info("WebSite: " + page.getWebsite());
			Location location = page.getLocation();
			if (location != null) {
				LOGGER.info("City: " + location.getCity());
				LOGGER.info("Country: " + location.getCountry());
				LOGGER.info("Street: " + location.getStreet());
				LOGGER.info("Zip: " + location.getZip());
			}

		}
	}

	@SuppressWarnings("unchecked")
	private void getAllInvitations(PagedList<Invitation> invitations) {
		for (Invitation invitation : invitations) {
			LOGGER.info("EventId: " + invitation.getEventId());
			LOGGER.info("Description:" + invitation.getExtraData().get("description"));
			LinkedHashMap<String, Object> place = (LinkedHashMap<String, Object>) invitation.getExtraData()
					.get("place");
			if (place != null) {
				LOGGER.info("Organizer Name:" + place.get("name"));

				LinkedHashMap<String, Object> location = (LinkedHashMap<String, Object>) place.get("location");
				if (location != null) {
					LOGGER.info("City: " + location.get("city"));
					LOGGER.info("Country: " + location.get("country"));
					LOGGER.info("Street: " + location.get("street"));
					LOGGER.info("zip: " + location.get("zip"));
				}
			}

			LOGGER.info("Event Name: " + invitation.getName());

		}
	}

	private void getAllLikes(PagedList<Page> likes) {
		for (Page like : likes) {
			LOGGER.info("Category: " + like.getCategory());
			LOGGER.info("Description:" + like.getDescription());
			LOGGER.info("Fan count: " + like.getFanCount());
			LOGGER.info("PageId: " + like.getId());
			LOGGER.info("Page Name: " + like.getName());
			LOGGER.info("WebSite: " + like.getWebsite());
			Location location = like.getLocation();
			if (location != null) {
				LOGGER.info("City: " + location.getCity());
				LOGGER.info("Country: " + location.getCountry());
				LOGGER.info("Street: " + location.getStreet());
			}

		}
	}

	private void displayUserProfile(User userProfile, customer.engagement.domain.User user) {

		LOGGER.info("\n\n\nUser Profile....");
		LOGGER.info("Facebook UserId: " + userProfile.getId());
		user.setUserId(userProfile.getId());
		LOGGER.info("Name: " + userProfile.getName());
		user.setEmail(userProfile.getEmail());
		LOGGER.info("Email: " + userProfile.getEmail());
		user.setName(userProfile.getName());
		LOGGER.info("Gender: " + userProfile.getGender());

		user.setGender(userProfile.getGender());

		LOGGER.info("Birthday: " + userProfile.getBirthday());
		user.setBirthday(userProfile.getBirthday());
		LOGGER.info("HomeTown: " + userProfile.getHometown().getName());
		user.setHomeTownLocation(userProfile.getHometown().getName());
		LOGGER.info("Current Location: " + userProfile.getLocation().getName());
		user.setCurrentLocation(userProfile.getLocation().getName());
		LOGGER.info("Cover Pics URL: " + userProfile.getCover().getSource());
		user.setCoverPicsURL(userProfile.getCover().getSource());
		LOGGER.info("Website: " + userProfile.getWebsite());
		user.setWebsite(userProfile.getWebsite());

		LOGGER.info("\n\nWorkPlace name... ");

		List<WorkDetail> workDetails = new ArrayList<>();
		for (WorkEntry work : userProfile.getWork()) {
			LOGGER.info("" + work.getEmployer().getName());
			WorkDetail workDetail = new WorkDetail();
			workDetail.setEmployerName(work.getEmployer().getName());
			workDetails.add(workDetail);
		}
		user.setWorkDetails(workDetails);

		LOGGER.info("\n\nEducation Details... ");
		List<EducationDetails> educationDetails = new ArrayList<>();
		for (EducationExperience exp : userProfile.getEducation()) {
			EducationDetails details = new EducationDetails();
			LOGGER.info("" + exp.getSchool().getName());
			details.setInstituteName(exp.getSchool().getName());
			details.setType(exp.getType());
			educationDetails.add(details);
		}

		LOGGER.info("=============\n\n\n");

	}

	/**
	 * Will display list of all user's page
	 * 
	 * @param accounts
	 *            list of all page accounts
	 */
	private void getAllPages(PagedList<Account> accounts, customer.engagement.domain.User user) {
		LOGGER.info("\n\n\nList of all pages of users...");
		List<UserPage> pages = new ArrayList<>();
		for (Account account : accounts) {
			UserPage userPage = new UserPage();
			LOGGER.info("Access Token: " + account.getAccessToken());
			userPage.setName(account.getName());
			LOGGER.info("Page Name: " + account.getName());
			LOGGER.info("Page Id: " + account.getId());
			// TODO double check
			userPage.setId(Long.valueOf(account.getId()));
			LOGGER.info("Page Category: " + account.getCategory());
			userPage.setCategory(account.getCategory());
			LOGGER.info("===========");
			pages.add(userPage);
		}
		user.setUserPage(pages);
		LOGGER.info("\n\n\n");
	}

}
