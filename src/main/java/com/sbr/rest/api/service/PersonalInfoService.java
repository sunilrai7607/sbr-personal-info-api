package com.sbr.rest.api.service;

import com.sbr.rest.api.model.response.PersonalInfoResponse;

public interface PersonalInfoService {

    PersonalInfoResponse findProfileById(final String id);
}
