package com.cosmichippos.persistence;

import com.cosmichippos.testUtils.Database;
import com.cosmichippos.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GenreDaoTest {

   /**
    * The Dao.
    */
   GenreDao dao;
   private final Logger logger = LogManager.getLogger(this.getClass());

   /**
    * Sets up.
    */
   @BeforeEach
   void setUp() {
       Database database = Database.getInstance();
       database.runSQL("/cleandb.sql");
       dao = new GenreDao();
   }

   /**
    * Verify getById successfully runs
    */
   @Test
   void getByIdSuccess() {
       Genre genre = dao.getById(19);
       assertNotNull(genre);
       assertEquals("Progressive Rock", genre.getName());
   }

   /**
    * Verify getByParentId
    */
   @Test
   void getByParentIdSuccess() {
       Genre genre = dao.getByParentId(6);
       Genre genre2 = dao.getById(5);
       long tester = genre2.getParentId();
       assertNotNull(genre);
       assertEquals(genre2, tester);
   }


   /**
    * Verify getByName
    */
   @Test
   void getByName() {
       Genre genre = dao.getByName("New Wave");
       Genre retrievedGenre = dao.getById(18);
       assertEquals(genre, retrievedGenre);
   }

   /**
    * Verify getAll genres
    */
   @Test
   void getAllSuccess() {
       List<Genre> genreList = dao.getAll();
       assertEquals(971, genreList.size());
   }

   /**
    * Verify saveOrUpdate genre
    */
   @Test
   void saveOrUpdateSuccess() {
       String newName = "Team music";
       Genre genreToUpdate = dao.getById(2);
       genreToUpdate.setName(newName);
       dao.saveOrUpdate(genreToUpdate);
       Genre retrievedGenre = dao.getById(2);
       assertEquals(genreToUpdate, retrievedGenre);
   }

//    /**
//     * Verify insert genre
//     */
//    @Test
//    void insertSuccess() {
//        Genre genre = new Genre(19, "Country Song",1);
//        long id = dao.insert(genre);
//        assertNotEquals(0, id);
//        Genre insertedGenre = dao.getById(id);
//        assertEquals(genre, insertedGenre);
//    }

   /**
    * Verify delete genre
    */
   @Test
   void deleteSuccess() {
       Genre genreToDelete = dao.getById(20);
       dao.delete(genreToDelete);
       assertNull(dao.getById(20));
   }
}


