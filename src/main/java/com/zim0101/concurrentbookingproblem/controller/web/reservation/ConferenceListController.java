package com.zim0101.concurrentbookingproblem.controller.web.reservation;

import com.zim0101.concurrentbookingproblem.model.Conference;
import com.zim0101.concurrentbookingproblem.repository.ConferenceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/conferences")
public class ConferenceListController {
    private final ConferenceRepository conferenceRepository;

    public ConferenceListController(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @ModelAttribute
    public List<Conference> addConferenceListToModel() {
        return conferenceRepository.findAll();
    }

    @GetMapping
    public String conferenceListView() {
        return "reservation/conferences";
    }
}