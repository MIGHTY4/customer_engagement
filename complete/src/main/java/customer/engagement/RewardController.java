package customer.engagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rewards")
public class RewardController {

	@RequestMapping("/{id}")
	@ResponseBody
	public String getRewardsPoint(@PathVariable("id") String id) {
		String response = "{\r\n" + "                \"responseCode\": \"SUCCESS\",\r\n"
				+ "                \"errors\": [],\r\n" + "                \"reward_points_status\": [\r\n"
				+ "                                                                                                                                {\r\n"
				+ "                                                                                                                                                \"customer_id\": 836466,\r\n"
				+ "                                                                                                                                                \"first_name\": \"Dunn\",\r\n"
				+ "                                                                                                                                                \"last_name\": \"Smith\",\r\n"
				+ "                                                                                                                                                \"credit_card_number\": \"xxx8-3211-6675-32xx\",\r\n"
				+ "                                                                                                                                                \"expiry_date\": \"08/21\",\r\n"
				+ "                                                                                                                                                \"current_month_year\": \"11/17\",\r\n"
				+ "                                                                                                                                                \"transactions_in_current_month_year\": 12,\r\n"
				+ "                                                                                                                                                \"reward_points_credit_status\": \"Y\"\r\n"
				+ "                                                                                                                                }\r\n"
				+ "                                                ],\r\n"
				+ "                \"reward_points_status_errors\": []\r\n" + "}";
		return response;
	}

}
