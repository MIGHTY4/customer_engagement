package customer.engagement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Account;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.GroupMembership;
import org.springframework.social.facebook.api.GroupOperations;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.WorkEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FacebookController {

	private Facebook facebook;
	private ConnectionRepository connectionRepository;

	public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}

	@GetMapping
	public String helloFacebook(Model model) {
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}

		String[] fields = { "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices",
				"education", "email", "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown",
				"inspirational_people", "installed", "install_type", "is_verified", "languages", "last_name", "link",
				"locale", "location", "meeting_for", "middle_name", "name", "name_format", "political", "quotes",
				"payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other",
				"sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits",
				"viewer_can_send_gift", "website", "work" };

		User userProfile = facebook.fetchObject("me", User.class, fields);
		displayUserProfile(userProfile);

		model.addAttribute("username", userProfile.getName());

		model.addAttribute("user", userProfile);


		PagedList<Account> accounts = facebook.pageOperations().getAccounts();
		Page page = facebook.pageOperations().getPage("930757893637831");
		getAllPages(accounts);

		//List of all groups created by users..
		PagedList<GroupMembership> groups = facebook.groupOperations().getMemberships();
		for (GroupMembership membership : groups) {
			System.out.println("Group Name: " + membership.getName());
		}
		
		//PagedList<GroupMembership> groupss = facebook.groupOperations().


		return "feed";
	}

	/**
	 * Will display list of all user's page
	 * 
	 * @param accounts
	 *            list of all page accounts
	 */
	private void getAllPages(PagedList<Account> accounts) {
		System.out.println("\n\n\nList of all pages of users...");
		for (Account account : accounts) {
			System.out.println("Access Token: " + account.getAccessToken());
			System.out.println("Page Name: " + account.getName());
			System.out.println("Page Id: " + account.getId());
			System.out.println("Page Category: " + account.getCategory());
			System.out.println("===========");
		}
		System.out.println("\n\n\n");
	}

	private void displayUserProfile(User userProfile) {
		System.out.println("\n\n\nUser Profile....");
		System.out.println("Facebook UserId: " + userProfile.getId());
		System.out.println("Name: " + userProfile.getName());
		System.out.println("Email: " + userProfile.getEmail());
		System.out.println("Gender: " + userProfile.getGender());
		System.out.println("Birthday: " + userProfile.getBirthday());
		System.out.println("HomeTown: " + userProfile.getHometown().getName());
		System.out.println("Current Location: " + userProfile.getLocation().getName());
		System.out.println("Cover Pics URL: " + userProfile.getCover().getSource());
		System.out.println("Website: " + userProfile.getWebsite());
		
		System.out.println("\n\nWorkPlace name... ");
		for (WorkEntry work : userProfile.getWork()) {
			System.out.println("" + work.getEmployer().getName());
		}

		System.out.println("\n\nEducation Details... ");
		for (EducationExperience exp : userProfile.getEducation()) {
			System.out.println("" + exp.getSchool().getName());
		}

		Map<String, Object> extras = userProfile.getExtraData();
		System.out.println(extras.get("context"));
		
		LinkedHashMap map = (LinkedHashMap) extras.get("context");
		LinkedHashMap mutual_likes =(LinkedHashMap)  map.get("mutual_likes");
		ArrayList data = (ArrayList)  mutual_likes.get("data");
	//	for(LinkedHashMap details: data)
		
		
	//	JSONObject jsonObject = (JSONObject) extras.get("context");
		
		System.out.println("=============\n\n\n");

	}

}
