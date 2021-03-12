package com.bootcamp.linkTracker.repositories;

import com.bootcamp.linkTracker.exceptions.URLNotFoundException;
import com.bootcamp.linkTracker.model.LinkDTO;

public interface ILinkTrackerRepository {

    LinkDTO saveLink(String url, String pass);

    LinkDTO findByLinkId(String linkId) throws URLNotFoundException;

    LinkDTO updateLink(LinkDTO link) throws URLNotFoundException;
}
