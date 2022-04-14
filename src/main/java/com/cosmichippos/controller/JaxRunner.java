package com.cosmichippos.controller;
import com.cosmichippos.entity.Genre;
import com.cosmichippos.persistence.GenreDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * The type Jax runner.
 */
@Path("/xml")
public class JaxRunner {

    /**
     * The Dao.
     */
    GenreDao dao;

    /**
     * Genre service.
     *
     * @param g the g
     * @return the string
     */
    @GET
    @Path("{g}")
    @Produces("application/xml")
    public String genreService(@PathParam("g") String g) {
        dao = new GenreDao();

        Genre inputGenre;
        long inputId;
        String inputName;
        long inputParent;
        String parentName;

        inputGenre = dao.getByName(g);

        if (inputGenre != null) {

            inputId = inputGenre.getId();
            inputName = inputGenre.getName();

            inputParent = inputGenre.getParentId() != null
                    ? inputGenre.getParentId() : 0;
            parentName = inputParent != 0
                    ? dao.getById(inputParent).getName() : "None";

        } else {

            inputId = 0;
            inputName = "No genre found...";
            inputParent = 0;
            parentName = "None";

        }

        return "<genreService>" + "<genreId>" + inputId + "</genreId>\n\n"
                        + "<genreName>" + inputName + "</genreName>\n\n"
                        + "<genreParentId>" + inputParent + "</genreParentId>\n\n"
                        + "<genreParentName>" + parentName + "</genreParentName>" + "</genreService>";
    }

}