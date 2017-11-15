package customer.engagement.service;

import org.springframework.stereotype.Service;

@Service
public class RewardServiceImpl implements RewardService {

	public String getRewardPoint(String id) {
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
