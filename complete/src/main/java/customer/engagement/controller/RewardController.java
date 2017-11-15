package customer.engagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import customer.engagement.service.RewardService;

@Controller
@RequestMapping("/rewards")
public class RewardController {

	@Autowired
	private RewardService rewardService;

	@RequestMapping("/{id}")
	@ResponseBody
	public String getRewardsPoint(@PathVariable("id") String id) {
		return rewardService.getRewardPoint(id);
	}

}
