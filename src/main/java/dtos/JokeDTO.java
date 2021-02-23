package dtos;

import entities.Joke;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JokeDTO {
    private long id;
    private String joke;
    private String answer;
    private String category;

    public JokeDTO(Joke j) {
        this.id = j.getId();
        this.joke = j.getJoke();
        this.answer = j.getAnswer();
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JokeDTO jokeDTO = (JokeDTO) o;
        return id == jokeDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "JokeDTO{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                ", answer='" + answer + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
