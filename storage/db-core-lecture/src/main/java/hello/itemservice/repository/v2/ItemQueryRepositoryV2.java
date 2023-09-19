package hello.itemservice.repository.v2;

import static hello.itemservice.domain.QItem.*;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import jakarta.persistence.EntityManager;

@Repository
public class ItemQueryRepositoryV2 {

    private final JPAQueryFactory query;

    public ItemQueryRepositoryV2(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Item> findAll(ItemSearchCond cond) {
        return query.select(item)
            .from(item)
            .where(
                containsItemName(cond.getItemName()),
                maxPrice(cond.getMaxPrice())
            )
            .fetch();
    }

    private BooleanExpression containsItemName(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return item.itemName.contains(itemName);
        }
        return null;
    }

    private BooleanExpression maxPrice(Integer maxPrice) {
        if (maxPrice == null) {
            return null;
        }
        return item.price.loe(maxPrice);
    }
}
