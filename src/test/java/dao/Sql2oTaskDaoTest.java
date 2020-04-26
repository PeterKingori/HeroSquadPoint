package dao;

import models.Hero;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oTaskDaoTest {
    private Sql2oHeroDao heroDao;
    private Connection conn;

    @BeforeClass
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
}
