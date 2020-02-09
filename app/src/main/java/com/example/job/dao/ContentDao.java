package com.example.job.dao;




import com.example.job.bean.Position;
import com.example.job.database.DBconn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContentDao {
    Connection conn;

    public ContentDao(String name){
        DBconn dBconn = new DBconn();
        this.conn = dBconn.getConnection(name);
    }
    //查询数据库中的所有商家发布任务
    public List<Position> showAllContent(String tableName){
        List<Position> list = new ArrayList<>();
        try {
//            String sql="select id,username,content from test";
            String sql="select * from "+tableName;
//            PreparedStatement ps = this.conn.prepareStatement(sql);

            Statement st = this.conn.createStatement();
            ResultSet rs=st.executeQuery(sql);
//            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Position position = new Position();
                position.setId(rs.getInt("id"));
                position.setClas(rs.getString("clas"));//职位分类
                position.setTitle(rs.getString("title"));//职位标题
                position.setTreatment(rs.getString("treatment"));//职位待遇
                position.setPeopleNum(rs.getInt("peopleNum"));//需求人数
                position.setEditdate(rs.getString("editdate"));//修改日期

                position.setPhone(rs.getString("phone"));//电话
                position.setImges(rs.getString("imges"));//修改日期
                position.setStartTime(rs.getString("startTime"));
                position.setEndTime(rs.getString("endTime"));
                position.setDescx(rs.getString("descx"));

                list.add(position);
            }


//            while (rs.next()){
//                Content content = new Content();
//                content.setId(rs.getInt("id"));
//                content.setUsername(rs.getString("username"));
//                content.setContent(rs.getString("content"));
//                list.add(content);
//            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
