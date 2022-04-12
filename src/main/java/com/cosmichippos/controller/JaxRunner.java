package com.cosmichippos.controller;
import com.cosmichippos.entity.Genre;
import com.cosmichippos.persistence.GenreDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/run")
public class JaxRunner {

    GenreDao dao;

    @GET
    @Path("{g}")
    @Produces("application/xml")
    public String genreService(@PathParam("g") String g) {
        dao = new GenreDao();

        Genre inputGenre = dao.getByName(g);
        long inputId = inputGenre.getId();
        String inputName = inputGenre.getName();
        long inputParent = inputGenre.getParentId();
        Genre parentIdr = dao.getById(inputParent);
        String parentName = parentIdr.getName();

        return "<genreService>" + "<genreId>" + inputId + "</genreId>\n\n"
                        + "<genreName>" + inputName + "</genreName>\n\n"
                        + "<genreParent>" + inputParent + "</genreParent>\n\n"
                        + "<genreParentName>" + parentName + "</genreParentName>" + "</genreService>";
    }

}