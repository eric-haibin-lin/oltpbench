/******************************************************************************
 *  Copyright 2015 by OLTPBenchmark Project     *
 * *
 *  Licensed under the Apache License, Version 2.0 (the "License");           *
 *  you may not use this file except in compliance with the License.          *
 *  You may obtain a copy of the License at     *
 * *
 *    http://www.apache.org/licenses/LICENSE-2.0*
 * *
 *  Unless required by applicable law or agreed to in writing, software       *
 *  distributed under the License is distributed on an "AS IS" BASIS,         *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 *  See the License for the specific language governing permissions and       *
 *  limitations under the License.              *
 ******************************************************************************/

package com.oltpbenchmark.benchmarks.hyadapt.procedures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.oltpbenchmark.api.Procedure;
import com.oltpbenchmark.api.SQLStmt;
import com.oltpbenchmark.benchmarks.hyadapt.HYADAPTConstants;

public class MaxRecord1 extends Procedure{
    
    public final SQLStmt readStmt = new SQLStmt(
            "SELECT MAX(FIELD198), MAX(FIELD206), MAX(FIELD169), MAX(FIELD119), MAX(FIELD9), MAX(FIELD220), MAX(FIELD2), MAX(FIELD230), MAX(FIELD212), MAX(FIELD164), MAX(FIELD111), MAX(FIELD136), MAX(FIELD106), MAX(FIELD8), MAX(FIELD112), MAX(FIELD4), MAX(FIELD234), MAX(FIELD147), MAX(FIELD35), MAX(FIELD114), MAX(FIELD89), MAX(FIELD127), MAX(FIELD144), MAX(FIELD71), MAX(FIELD186) "
                    + "FROM HTABLE WHERE FIELD1>?" );
    
    public void run(Connection conn, int keyname) throws SQLException {
        PreparedStatement stmt = this.getPreparedStatement(conn, readStmt);
        stmt.setInt(1, keyname);          
        ResultSet r=stmt.executeQuery();
        int max = r.getInt(1);        
        r.close();
    }

}
