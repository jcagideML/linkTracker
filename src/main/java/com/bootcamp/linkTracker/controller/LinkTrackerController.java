package com.bootcamp.linkTracker.controller;

import com.bootcamp.linkTracker.exceptions.*;
import com.bootcamp.linkTracker.model.LinkDTO;
import com.bootcamp.linkTracker.model.LinkRequestDTO;
import com.bootcamp.linkTracker.services.ILinkTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class LinkTrackerController {

    @Autowired
    private ILinkTrackerService service;

    @PostMapping("/create")
    public LinkDTO createLink(@RequestBody LinkRequestDTO link) throws URLNotFoundException, InvalidURLException {
        return service.create(link.getRequest(), link.getPass());
    }

    @GetMapping(value = "link/{linkId}", params = {"pass"})
    public ResponseEntity<Object> redirect(@PathVariable String linkId, @RequestParam("pass") String pass) throws URLNotFoundException, PassNotRightException {

        String url = service.redirect(linkId, pass).getUrl();

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

    @GetMapping(value = "link/{linkId}")
    public ResponseEntity<Object> redirect(@PathVariable String linkId) throws URLNotFoundException {

        String url = service.redirect(linkId).getUrl();

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

    @GetMapping("/metrics/{linkId}")
    public Integer metrics(@PathVariable String linkId) throws URLNotFoundException {
        return service.metrics(linkId).getVisites();
    }

    @PostMapping("/invalidate")
    public LinkDTO invalidate(@RequestBody LinkRequestDTO request) throws URLNotFoundException {
        return service.invalidate(request.getRequest());
    }

    @ExceptionHandler(ILinkTrackerException.class)
    public ResponseEntity<ErrorDTO> handleException(ILinkTrackerException exception) {
        return new ResponseEntity<>(exception.getError(), exception.getStatus());
    }
}