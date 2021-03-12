package com.bootcamp.linkTracker.repositories;

import com.bootcamp.linkTracker.exceptions.URLNotFoundException;
import com.bootcamp.linkTracker.model.LinkDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class LinkTrackerRepository implements ILinkTrackerRepository {

    private final List<LinkDTO> links;

    public LinkTrackerRepository() {
        this.links = new ArrayList<>();
    }

    @Override //Lo uso como una especie de "registry".
    //Si ya existe, devuelvo el creado, no largo un error de duplicado.
    public LinkDTO saveLink(String url, String pass) {
        for (LinkDTO l : links) {
            if (l.getUrl().equals(url)) {
                return l;
            }
        }
        LinkDTO link = new LinkDTO();
        link.setLinkId(this.links.size() + 1);
        link.setUrl(url);
        link.setState("active");
        link.setVisites(0);
        link.setPass(pass);
        this.links.add(link);
        return link;
    }


    @Override
    public LinkDTO updateLink(LinkDTO link) throws URLNotFoundException {
        for (LinkDTO l : links) {
            if (l.getLinkId().equals(link.getLinkId())) {
                l.setState(link.getState());
                l.setVisites(link.getVisites());
                return l;
            }
        }
        throw new URLNotFoundException();
    }

    @Override //Hacemos uso de URLNotFoundException.
    public LinkDTO findByLinkId(String linkId) throws URLNotFoundException {
        for (LinkDTO l : links) {
            if (l.getLinkId().equals(Integer.parseInt(linkId))) {
                return l;
            }
        }
        throw new URLNotFoundException();
    }
}