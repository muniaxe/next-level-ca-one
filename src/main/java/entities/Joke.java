package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jokes")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String joke;
    private String answer;
    private String category;

    //Secret value not to display in DTO.
    private String secret;

    public Joke() {
    }

    public Joke(String joke, String answer, String category, String secret) {
        this.joke = joke;
        this.answer = answer;
        this.category = category;
        this.secret = secret;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joke joke = (Joke) o;
        return Objects.equals(id, joke.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder {
        private String joke;
        private String answer;
        private String category;
        private String secret;

        public Builder() {
        }

        public Builder withJoke(String joke) {
            this.joke = joke;
            return this;
        }

        public Builder withSecret(String secret) {
            this.secret = secret;
            return this;
        }

        public Builder withAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Joke build() {
            return new Joke(this.joke, this.answer, this.category, this.secret);
        }
    }
}
