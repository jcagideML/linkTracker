package com.bootcamp.linkTracker.services;

import com.bootcamp.linkTracker.exceptions.InvalidURLException;
import com.bootcamp.linkTracker.exceptions.PassNotRightException;
import com.bootcamp.linkTracker.exceptions.URLNotFoundException;
import com.bootcamp.linkTracker.model.LinkDTO;

public interface ILinkTrackerService {

    public LinkDTO create(String s, String pass) throws URLNotFoundException, InvalidURLException;

    public LinkDTO redirect(String s, String pass) throws URLNotFoundException, PassNotRightException;

    public LinkDTO redirect(String s) throws URLNotFoundException;

    public LinkDTO metrics(String s) throws URLNotFoundException;

    public LinkDTO invalidate(String s) throws URLNotFoundException;

}
