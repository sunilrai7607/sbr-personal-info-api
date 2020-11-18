package com.sbr.rest.api.controller;

import com.sbr.rest.api.model.response.PersonalInfoResponse;
import com.sbr.rest.api.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/personal-info", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonalInfoController {

    private final PersonalInfoService personalInfoService;

    @Autowired
    public PersonalInfoController(PersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalInfoResponse> getProfileById(@PathVariable("id") String id) {
        return ResponseEntity.ok(personalInfoService.findProfileById(id));
    }
}
