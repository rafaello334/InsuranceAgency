package pl.agencja.client.model.users;

public class Admin
{
	private static String login = "admin";
	private static String password = "admin";

	public static String getLogin()
	{
		return login;
	}

	public static void setLogin(String login)
	{
		Admin.login = login;
	}

	public static String getPassword()
	{
		return password;
	}

	public static void setPassword(String password)
	{
		Admin.password = password;
	}

}
