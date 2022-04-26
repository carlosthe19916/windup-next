package org.jboss.windup.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathUtilTest
{
    String windupHome = null;
    String userHome = null;

    @BeforeEach
    public void before()
    {
        System.clearProperty("windup.home");
        userHome = System.clearProperty("user.home");
    }

    @AfterEach
    public void after()
    {
        if (userHome != null)
            System.setProperty("user.home", userHome);
        if (windupHome != null)
            System.setProperty("windup.home", windupHome);
    }

    private String setWindupHome(String path)
    {
        return System.setProperty("windup.home", path);
    }

    private String setUserHome(String path)
    {
        return System.setProperty("user.home", path);
    }

    /*
     * Begin test cases
     */

    @Test
    public void testWindupHome()
    {
        assertEquals(Paths.get(""), PathUtil.getWindupHome());
        setWindupHome("/foo");
        assertEquals(Paths.get("/foo"), PathUtil.getWindupHome());
    }

    @Test
    public void testWindupHomeRules()
    {
        assertEquals(Paths.get("rules"), PathUtil.getWindupRulesDir());
        setWindupHome("/foo");
        assertEquals(Paths.get("/foo", "rules"), PathUtil.getWindupRulesDir());
    }

    @Test
    public void testWindupHomeIgnoreListDir()
    {
        assertEquals(Paths.get("ignore"), PathUtil.getWindupIgnoreDir());
        setWindupHome("/foo");
        assertEquals(Paths.get("/foo", "ignore"), PathUtil.getWindupIgnoreDir());
    }

    @Test
    public void testWindupUserDir()
    {
        assertEquals(Paths.get(""), PathUtil.getWindupUserDir());
        setUserHome("/foo");
        assertEquals(Paths.get("/foo", ".mta"), PathUtil.getWindupUserDir());
    }

    @Test
    public void testWindupIgnoreDir()
    {
        assertEquals(Paths.get("ignore"), PathUtil.getUserIgnoreDir());
        setUserHome("/foo");
        assertEquals(Paths.get("/foo", ".mta", "ignore"), PathUtil.getUserIgnoreDir());
    }
}
