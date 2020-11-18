package com.sbr.rest.api.service.impl;

import com.sbr.rest.api.client.ProfileClient;
import com.sbr.rest.api.model.response.PersonalInfoResponse;
import com.sbr.rest.api.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

    private final ProfileClient profileClient;

    @Autowired
    public PersonalInfoServiceImpl(ProfileClient profileClient) {
        this.profileClient = profileClient;
    }

    @Override
    public PersonalInfoResponse findProfileById(final String id) {
        return new PersonalInfoResponse(profileClient.getProfileById(id));
    }
}
