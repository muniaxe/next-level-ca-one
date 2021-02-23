package dtos;

import entities.Joke;

import java.util.ArrayList;
import java.util.List;

public class JokeDTO {
    private long id;
    private String joke;
    private String reference;
    private String category;

    public JokeDTO(Joke j) {
        this.id = j.getId();
        this.joke = j.getJoke();
        this.reference = j.getReference();
        this.category = j.getCategory();
    }

    public static List<JokeDTO> toList(List<Joke> jokes){
        List<JokeDTO> list = new ArrayList<>();
        jokes.forEach(j -> list.add(new JokeDTO(j)));
        return list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "JokeDTO{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                ", reference='" + reference + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
