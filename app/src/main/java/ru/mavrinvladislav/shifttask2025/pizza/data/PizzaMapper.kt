package ru.mavrinvladislav.shifttask2025.pizza.data

import ru.mavrinvladislav.shifttask2025.BuildConfig
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.model.DoughDto
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.model.IngredientDto
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.model.PizzaDto
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.model.SizeDto
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.model.ToppingDto
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Dough
import ru.mavrinvladislav.shifttask2025.shared.domain.model.DoughType
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Ingredient
import ru.mavrinvladislav.shifttask2025.shared.domain.model.IngredientType
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Size
import ru.mavrinvladislav.shifttask2025.shared.domain.model.SizeType
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Topping
import ru.mavrinvladislav.shifttask2025.shared.domain.model.ToppingType

fun PizzaDto.toDomain(): Pizza {
    return Pizza(
        id = this.id,
        name = this.name,
        description = this.description,
        sizes = this.sizes.map { it.toDomain() },
        doughs = this.doughs.map { it.toDomain() },
        ingredients = this.ingredients.map { it.toDomain() },
        toppings = this.toppings.map { it.toDomain() },
        calories = this.calories,
        protein = this.protein,
        totalFat = this.totalFat,
        carbohydrates = this.carbohydrates,
        sodium = this.sodium,
        allergens = this.allergens,
        isVegetarian = this.isVegetarian,
        isGlutenFree = this.isGlutenFree,
        isNew = this.isNew,
        isHit = this.isHit,
        imgUrl = BuildConfig.BASE_URL + this.img
    )
}

fun IngredientDto.toDomain(): Ingredient {
    return Ingredient(
        type = IngredientType.valueOf(this.type),
        price = this.price,
        imgUrl = BuildConfig.BASE_URL + this.img
    )
}

fun ToppingDto.toDomain(): Topping {
    return Topping(
        type = ToppingType.valueOf(this.type),
        price = this.price,
        imgUrl = BuildConfig.BASE_URL + this.img
    )
}

fun SizeDto.toDomain(): Size {
    return Size(
        type = SizeType.valueOf(this.type),
        price = this.price
    )
}

fun DoughDto.toDomain(): Dough {
    return Dough(
        type = DoughType.valueOf(this.type),
        price = this.price
    )
}
