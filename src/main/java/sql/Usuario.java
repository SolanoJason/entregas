package sql;

public class Usuario {
	public final int id;
	public final String nickname;
	public final String contra;
	public final String salt;
	public final String Pista;

	public Usuario(int id, String nickname, String contra, String salt, String pista) {
		this.id = id;
		this.nickname = nickname;
		this.contra = contra;
		this.salt = salt;
		Pista = pista;
	}
}
