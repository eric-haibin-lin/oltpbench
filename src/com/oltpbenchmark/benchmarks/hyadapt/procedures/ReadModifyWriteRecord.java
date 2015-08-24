/******************************************************************************
 *  Copyright 2015 by OLTPBenchmark Project                                   *
 *                                                                            *
 *  Licensed under the Apache License, Version 2.0 (the "License");           *
 *  you may not use this file except in compliance with the License.          *
 *  You may obtain a copy of the License at                                   *
 *                                                                            *
 *    http://www.apache.org/licenses/LICENSE-2.0                              *
 *                                                                            *
 *  Unless required by applicable law or agreed to in writing, software       *
 *  distributed under the License is distributed on an "AS IS" BASIS,         *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 *  See the License for the specific language governing permissions and       *
 *  limitations under the License.                                            *
 ******************************************************************************/

package com.oltpbenchmark.benchmarks.hyadapt.procedures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;

public class ReadModifyWriteRecord extends Procedure {
    public final SQLStmt selectStmt = new SQLStmt(
        "Select * from HTABLE where H_KEY=? for update"
    );
    public final SQLStmt updateAllStmt = new SQLStmt(
        "UPDATE HTABLE SET FIELD1=?,FIELD2=?,FIELD3=?,FIELD4=?,FIELD5=?," +
        "FIELD6=?,FIELD7=?,FIELD8=?,FIELD9=?,FIELD10=? WHERE H_KEY=?"
    );
	//FIXME: The value in ysqb is a byteiterator
    public void run(Connection conn, int keyname, String fields[], Map<Integer,String> results) throws SQLException {
        PreparedStatement stmt = this.getPreparedStatement(conn, selectStmt);
        stmt.setInt(1, keyname);          
        ResultSet r = stmt.executeQuery();
        while (r.next()) {
        	for (int i = 1; i < 11; i++)
        	    results.put(i, r.getString(i));
        }
        r.close();
        stmt= this.getPreparedStatement(conn, updateAllStmt);
        stmt.setInt(11, keyname);
        
        for (int i = 0; i < fields.length; i++) {
        	stmt.setString(i+1, fields[i]);
        }
        stmt.executeUpdate();
    }

}