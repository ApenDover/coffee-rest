package ts.andrey.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ts.andrey.common.data.entity.Dessert;
import ts.andrey.common.data.entity.Ordering;
import ts.andrey.common.dto.OrderingDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderingToOrderingDtoMapper {

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "milk.id", target = "milkId")
    @Mapping(source = "drink.id", target = "drinkId")
    @Mapping(source = "syrup.id", target = "syrupId")
    @Mapping(source = "desserts", target = "dess", qualifiedByName = "ToIntegerArray")
    OrderingDTO toOrderingDTO(Ordering ordering);

    @Named("ToIntegerArray")
    default Integer[] toIntegerArray(List<Dessert> desserts) {
        if (desserts == null) {
            return new Integer[0];
        }
        return desserts.stream()
                .map(Dessert::getId)
                .toArray(Integer[]::new);
    }

}
