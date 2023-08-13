package com.srh.api.dto.resource;

import com.srh.api.model.Evaluator;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "evaluators")
public class EvaluatorDto {
    private final Integer id;
    private final String name;
    private final String login;
    private final String email;

    public EvaluatorDto(Evaluator evaluator) {
        this.id = evaluator.getId();
        this.name = evaluator.getName();
        this.login = evaluator.getLogin();
        this.email = evaluator.getEmail();
    }

    public static Page<EvaluatorDto> convert(Page<Evaluator> users) {
        return users.map(EvaluatorDto::new);
    }
}
