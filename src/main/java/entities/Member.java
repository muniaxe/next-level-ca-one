package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Member.deleteAllRows", query = "DELETE from Member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Member() {
    }

    private String name;
    private String email;
    @ElementCollection
    private List<String> favoriteGames;


    public Member(String name, String email, List<String> favoriteGames) {
        this.name = name;
        this.email = email;
        this.favoriteGames = favoriteGames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<String> getFavoriteGames() {
        return favoriteGames;
    }

    public void setFavoriteGames(List<String> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
