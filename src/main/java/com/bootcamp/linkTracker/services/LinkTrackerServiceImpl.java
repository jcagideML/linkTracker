package com.bootcamp.linkTracker.services;

import com.bootcamp.linkTracker.exceptions.InvalidURLException;
import com.bootcamp.linkTracker.exceptions.PassNotRightException;
import com.bootcamp.linkTracker.exceptions.URLNotFoundException;
import com.bootcamp.linkTracker.model.LinkDTO;
import com.bootcamp.linkTracker.repositories.ILinkTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LinkTrackerServiceImpl implements ILinkTrackerService {

    @Autowired
    private final ILinkTrackerRepository linkTrackerRepo;

    public LinkTrackerServiceImpl(ILinkTrackerRepository linkTrackerRepo) {
        this.linkTrackerRepo = linkTrackerRepo;
    }

    @Override
    public LinkDTO create(String s, String pass) throws InvalidURLException {

        Pattern p = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?(https://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?+%/.\\w]+)?");

        Matcher m = p.matcher(s);

        if (m.matches()) {
            return linkTrackerRepo.saveLink(s, pass);
        } else {
            throw new InvalidURLException();
        }
    }

    @Override
    public LinkDTO redirect(String s, String pass) throws URLNotFoundException, PassNotRightException {
        LinkDTO link = linkTrackerRepo.findByLinkId(s);
        if (link.getPass().equals(pass)) {
            link.setVisites(link.getVisites() + 1);
            linkTrackerRepo.updateLink(link);
            return link;
        }

        throw new PassNotRightException();
    }

    @Override
    public LinkDTO redirect(String s) throws URLNotFoundException {
        LinkDTO link = linkTrackerRepo.findByLinkId(s);
        link.setVisites(link.getVisites() + 1);
        linkTrackerRepo.updateLink(link);
        return link;
    }

    @Override
    public LinkDTO metrics(String s) throws URLNotFoundException {
        return linkTrackerRepo.findByLinkId(s);
    }

    @Override
    public LinkDTO invalidate(String s) throws URLNotFoundException {
        LinkDTO link = linkTrackerRepo.findByLinkId(s);
        link.setState("invalidated");
        linkTrackerRepo.updateLink(link);
        return link;

    }
}