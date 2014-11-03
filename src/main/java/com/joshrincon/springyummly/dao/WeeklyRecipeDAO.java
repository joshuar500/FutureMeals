package com.joshrincon.springyummly.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("weeklyRecipesDao")
public class WeeklyRecipeDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public WeeklyRecipeDAO() {
        System.out.println("Successlaslkjdhasd");
    }

    @Autowired
    public void setDataSource(DataSource jdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public List<WeeklyRecipe> getWeeklyRecipes(){
        return jdbcTemplate.query("select * from week", new RowMapper<WeeklyRecipe>() {
            @Override
            public WeeklyRecipe mapRow(ResultSet resultSet, int i) throws SQLException {
                WeeklyRecipe weeklyRecipe = new WeeklyRecipe();

                weeklyRecipe.setId(resultSet.getInt("id"));
                weeklyRecipe.setId_mon(resultSet.getString("id_mon"));
                weeklyRecipe.setId_tue(resultSet.getString("id_tue"));
                weeklyRecipe.setId_wed(resultSet.getString("id_wed"));
                weeklyRecipe.setId_thu(resultSet.getString("id_thu"));
                weeklyRecipe.setId_fri(resultSet.getString("id_fri"));
                weeklyRecipe.setId_sat(resultSet.getString("id_sat"));
                weeklyRecipe.setId_sun(resultSet.getString("id_sun"));

                return weeklyRecipe;
            }
        });
    }
    public WeeklyRecipe getWeeklyRecipe(int id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject("select * from week where id = :id", params, new RowMapper<WeeklyRecipe>() {
            @Override
            public WeeklyRecipe mapRow(ResultSet resultSet, int i) throws SQLException {
                WeeklyRecipe weeklyRecipe = new WeeklyRecipe();

                weeklyRecipe.setId(resultSet.getInt("id"));
                weeklyRecipe.setId_mon(resultSet.getString("id_mon"));
                weeklyRecipe.setId_tue(resultSet.getString("id_tue"));
                weeklyRecipe.setId_wed(resultSet.getString("id_mon"));
                weeklyRecipe.setId_thu(resultSet.getString("id_tue"));
                weeklyRecipe.setId_fri(resultSet.getString("id_mon"));
                weeklyRecipe.setId_sat(resultSet.getString("id_tue"));
                weeklyRecipe.setId_sun(resultSet.getString("id_mon"));

                return weeklyRecipe;
            }
        });
    }
    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update("delete from week where id=:id", params) == 1;
    }
    public boolean create(WeeklyRecipe weeklyRecipe) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(weeklyRecipe);
        return jdbcTemplate.update("insert into week (id_mon, id_tue, id_wed, id_thu, id_fri, id_sat, id_sun) values (:id_mon, :id_tue, :id_wed, :id_thu, :id_fri, :id_sat, :id_sun)", params) == 1;
    }
    // @Transaction means this method will succeed or fail -- it won't half ass the work
    @Transactional
    public int[] create(List<WeeklyRecipe> weeklyRecipes) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(weeklyRecipes.toArray());
        return jdbcTemplate.batchUpdate("insert into week (id, id_mon, id_tue, id_wed, id_thu, id_fri, id_sat, id_sun) values (:id, :id_mon, :id_tue, :id_wed, :id_thu, :id_fri, :id_sat, :id_sun)", params);
    }
    public boolean update(WeeklyRecipe weeklyRecipe) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(weeklyRecipe);
        return jdbcTemplate.update("update week set id_mon=:id_mon, id_tue=:id_tue, id_wed=:id_wed, id_thu=:id_thu, id_fri=:id_fri, id_sat=:id_sat, id_sun=:id_sun where id=:id", params) == 1;
    }
}
