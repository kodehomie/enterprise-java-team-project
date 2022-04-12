package controller;
import entity.Genre;
import persistence.GenreDao;

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
        Genre fTest = dao.getByName(g);


        String result = "@Produces(\"application/xml\") Output:\n\n" + g + "\n\n";
        return "<genreService>" + "<genre>" + result + "</genre>" + "</genreService>";
    }

}