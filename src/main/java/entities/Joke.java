package entities;

import javax.persistence.*;

@Entity
@Table(name = "jokes")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String joke;
    private String reference;
    private String category;

    //Secret value not to display in DTO.
    private String secret;

    public Joke() {
    }

    public Joke(String joke, String reference, String category, String secret) {
        this.joke = joke;
        this.reference = reference;
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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public static class Builder {
        private String joke;
        private String reference;
        private String category;
        private String secret;

        private Builder withJoke(String joke) {
            this.joke = joke;
            return this;
        }

        private Builder withSecret(String secret) {
            this.secret = secret;
            return this;
        }

        private Builder withReference(String reference) {
            this.reference = reference;
            return this;
        }

        private Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        private Joke build() {
            return new Joke(this.joke, this.reference, this.category, this.secret);
        }
    }
}
