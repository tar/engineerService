package ru.ics.engineerService.model;

/*
 * Class describes server instance
 *  @author Lukashin {@link= "an.lukashin@gmail.com"}
 */

public class TargetServer
{
    public final String Hostname;
    public final String Username;
    public final String Password;

    public TargetServer(String hostname, String username, String password)
    {
        this.Hostname = hostname;
        this.Username = username;
        this.Password = password;
    }
}
