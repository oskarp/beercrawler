package se.oskarp.beerapi.beer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by oskar on 29/05/15.
 *
 *
 * TODO: Map this to what the PHP service expects
 *  $table->increments('id');
    $table->string('name');
    $table->string('description');
    $table->integer('style_id');
    $table->integer('drink_number')->uniqe();
    $table->integer('price');
    $table->integer('brewery_id');
    $table->integer('country_id');
    $table->integer('abv');
 *
 *
 *
 */
public class Beer {
    private String name;
    private String description;
    private String style;
    private int drink_number;
    private double price;
    private String brewery;
    private String country;
    private String origin;
    private double abv;
    private String packaging;
    private String varugrupp;
    private boolean ekologisk;
    private double volym;
    private Date salestart;
    private String supplier;

    public Beer() {}

    public Beer(String name, String description, String style, int drink_number, double price, String brewery,
                String country, String origin, double abv, String packaging, String varugrupp, boolean ekologisk,
                double volym, Date salestart,String supplier) {

        this.name = name;
        this.description = description;
        this.style = style;
        this.drink_number = drink_number;
        this.price = price;
        this.brewery = brewery;
        this.country = country;
        this.origin = origin;
        this.abv = abv;
        this.packaging = packaging;
        this.varugrupp = varugrupp;
        this.ekologisk = ekologisk;
        this.volym = volym;
        this.salestart = salestart;
        this.supplier = supplier;
    }

    /**
     * Converts the Beer object to a Map
     */
    public Map<String, Object> toMap()  throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Method[] methods = this.getClass().getMethods();

        Map<String, Object> map = new HashMap<String, Object>();
        for (Method m : methods) {
            if (m.getName().startsWith("get") && !m.getName().startsWith("getClass")) {
                Object value = m.invoke(this);
                map.put(m.getName().substring(3), value);
            }
        }

        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getDrink_number() {
        return drink_number;
    }

    public void setDrink_number(int drink_number) {
        this.drink_number = drink_number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getVarugrupp() {
        return varugrupp;
    }

    public void setVarugrupp(String varugrupp) {
        this.varugrupp = varugrupp;
    }

    public boolean getEkologisk() {
        return ekologisk;
    }

    public void setEkologisk(boolean ekologisk) {
        this.ekologisk = ekologisk;
    }

    public double getVolym() {
        return volym;
    }

    public void setVolym(double volym) {
        this.volym = volym;
    }

    public Date getSalestart() {
        return salestart;
    }

    public void setSalestart(Date salestart) {
        this.salestart = salestart;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return Objects.equals(drink_number, beer.drink_number) &&
                Objects.equals(price, beer.price) &&
                Objects.equals(abv, beer.abv) &&
                Objects.equals(ekologisk, beer.ekologisk) &&
                Objects.equals(volym, beer.volym) &&
                Objects.equals(name, beer.name) &&
                Objects.equals(description, beer.description) &&
                Objects.equals(style, beer.style) &&
                Objects.equals(brewery, beer.brewery) &&
                Objects.equals(country, beer.country) &&
                Objects.equals(origin, beer.origin) &&
                Objects.equals(packaging, beer.packaging) &&
                Objects.equals(varugrupp, beer.varugrupp) &&
                Objects.equals(salestart, beer.salestart) &&
                Objects.equals(supplier, beer.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, style, drink_number, price, brewery, country, origin, abv, packaging, varugrupp, ekologisk, volym, salestart, supplier);
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", style='" + style + '\'' +
                ", drink_number=" + drink_number +
                ", price=" + price +
                ", brewery='" + brewery + '\'' +
                ", country='" + country + '\'' +
                ", origin='" + origin + '\'' +
                ", abv=" + abv +
                ", packaging='" + packaging + '\'' +
                ", varugrupp='" + varugrupp + '\'' +
                ", ekologisk=" + ekologisk +
                ", volym=" + volym +
                ", salestart=" + salestart +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}