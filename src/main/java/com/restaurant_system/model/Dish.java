package com.restaurant_system.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.dateTime DESC"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId"),
        @NamedQuery(name = Dish.GET_BETWEEN, query = "SELECT d FROM Dish d " +
                "WHERE d.restaurant.id=:restaurantId AND d.dateTime BETWEEN :startDate AND :endDate ORDER BY d.dateTime DESC"),
//        @NamedQuery(name = Dish.UPDATE, query = "UPDATE Dish d SET d.dateTime = :datetime, d.price= :price," +
//                "d.description=:desc where d.id=:id and d.restaurant.id=:restaurantId")
})
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date_time"}, name = "dishes_unique_restaurant_datetime_idx")})
public class Dish extends AbstractBaseEntity {
    public static final String ALL_SORTED = "Dish.getAll";
    public static final String DELETE = "Dish.delete";
    public static final String GET_BETWEEN = "Dish.getBetween";

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 50000)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private com.restaurant_system.model.Restaurant restaurant;

    public Dish() {
    }

    public Dish(LocalDateTime dateTime, String description, int price) {
        this(null, dateTime, description, price);
    }

    public Dish(Integer id, LocalDateTime dateTime, String description, int price) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public com.restaurant_system.model.Restaurant getRestaurant() {
        return restaurant;
    }

    public void setUser(com.restaurant_system.model.Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}

