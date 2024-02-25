package com.zim0101.concurrentbookingproblem.controller.web.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminDashBoardController {
    @GetMapping
    public String dashboard() {
        return "dashboard/admin";
    }
}