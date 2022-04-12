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
    public String genreService(@PathParam("g") int g) {

        dao = new GenreDao();
        Genre fTest = dao.getById(g);
        String gTest = fTest.getName();

        String result = "@Produces(\"application/xml\") Output:\n\n" + gTest + "\n\n";
        return "<genreService>" + "<genre>" + result + "</genre>" + "</genreService>";
    }

}