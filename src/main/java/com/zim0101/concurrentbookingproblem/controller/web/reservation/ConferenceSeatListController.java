package com.zim0101.concurrentbookingproblem.controller.web.reservation;

import com.zim0101.concurrentbookingproblem.model.Conference;
import com.zim0101.concurrentbookingproblem.model.Seat;
import com.zim0101.concurrentbookingproblem.repository.ConferenceRepository;
import com.zim0101.concurrentbookingproblem.repository.SeatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/conference/{id}/seats")
public class ConferenceSeatListController {

    private static final Logger log = LoggerFactory.getLogger(ConferenceSeatListController.class);


    private final ConferenceRepository conferenceRepository;

    private final SeatRepository seatRepository;

    public ConferenceSeatListController(ConferenceRepository conferenceRepository,
                                        SeatRepository seatRepository) {
        this.conferenceRepository = conferenceRepository;
        this.seatRepository = seatRepository;
    }

    @ModelAttribute
    public List<Seat> addSeatListToModel(@PathVariable Integer id) {
        Conference conference = conferenceRepository.findById(id).orElseThrow();
        return seatRepository.findByConference(conference);
    }

    @GetMapping
    public String conferenceSeatListView() {
        return "reservation/seats";
    }
}