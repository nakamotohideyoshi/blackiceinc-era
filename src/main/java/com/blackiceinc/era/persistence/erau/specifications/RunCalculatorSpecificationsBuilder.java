package com.blackiceinc.era.persistence.erau.specifications;

import com.blackiceinc.era.persistence.erau.model.RunCalculator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public class RunCalculatorSpecificationsBuilder {

    private final List<SpecSearchCriteria> params;

    public RunCalculatorSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public final RunCalculatorSpecificationsBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) // the operation may be complex operation
            {
                final boolean startWithAsterisk = prefix.contains("*");
                final boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<RunCalculator> build() {
        if (params.size() == 0) {
            return null;
        }

        final List<Specification<RunCalculator>> specs = new ArrayList<>();
        for (final SpecSearchCriteria param : params) {
            specs.add(new RunCalculatorSpecification(param));
        }

        Specification<RunCalculator> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }

}
