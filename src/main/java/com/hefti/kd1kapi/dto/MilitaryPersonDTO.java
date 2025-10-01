package com.hefti.kd1kapi.dto;

import java.time.LocalDate;

public record MilitaryPersonDTO(
        Long id,
        String fullName,
        String warName,
        String cpf,
        LocalDate birthDate
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String fullName;
        private String warName;
        private String cpf;
        private LocalDate birthDate;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }
        public Builder warName(String warName) {
            this.warName = warName;
            return this;
        }
        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }
        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public MilitaryPersonDTO build() {
            return new MilitaryPersonDTO(
                    id, fullName, warName, cpf, birthDate
            );
        }
    }

}
