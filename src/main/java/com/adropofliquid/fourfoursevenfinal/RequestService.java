package com.adropofliquid.fourfoursevenfinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RequestService {

    @Autowired
    PersonelRepo personelRepo;

    @Autowired
    RequestRepo requestRepo;


    public String makeRequest(Request request) {
        if(request.getLanguage() == null)
            request.setLanguage("English");

        String error = "";

        if(!checkPersonel(request.getPersonel())){
            error = Errors.ONLY_ACADEMY_STAFF;
        }
        else {
            error = checkInputErrorsOrSave(request);
        }

        return error;
    }

    private String checkInputErrorsOrSave(Request request) {
        requestRepo.save(request);
        return Errors.SUCCESS;
    }

    private boolean checkPersonel(String personel) {
        return personelRepo.existsPersonelByPersonel(personel);
    }

    public List<Request> getAll() {
        List<Request> requests = new ArrayList<>();
        requestRepo.findAll().forEach(requests::add);
        return requests;
    }
}