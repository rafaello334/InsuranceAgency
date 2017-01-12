package pl.agencja.client.model.users;

public class Admin
{
	private static String login = "123";
	private static String password = "123";

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
