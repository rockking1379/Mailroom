#region Statements
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SQLite;
using System.Windows;
using System.Data.SqlClient;
using System.Data;
using System.Data.Sql;
#endregion

namespace Common
{
    public class DatabaseManager : Object
    {
        #region Variables
        string location;
        #endregion
        #region Constructor
        public DatabaseManager()
        {
            //Eh
        }
        public DatabaseManager(string location)
        {
            this.location = location;
        }
        #endregion

        public DataSet getPackages(bool allStops)
        {
            DataSet dSet = null;
            SQLiteConnection con = null;
            SQLiteCommand cmd = null;
            SQLiteDataAdapter dAdapt = null;

            DateTime cDate = new DateTime();
            DateTime date = new DateTime(cDate.Year, cDate.Month, cDate.Day);

            try
            {
                if (allStops)
                {
                    string statement = "select * from Package";
                    con = new SQLiteConnection("Data Source=" + location);
                    con.Open();
                    cmd = new SQLiteCommand(statement, con);
                    dSet = new DataSet();
                    dAdapt = new SQLiteDataAdapter(cmd);
                    dAdapt.Fill(dSet);
                }
                else
                {
                }
            }
            catch (SQLiteException e)
            {
                Console.WriteLine("Error: {0}", e.Message);
            }
            finally
            {
                if (dAdapt != null)
                {
                    dAdapt.Dispose();
                }
                if (cmd != null)
                {
                    cmd.Dispose();
                }
                if (con != null)
                {
                    con.Close();
                }
            }

            return dSet;
        }

        #region User Interaction
        public User login(string uName, int pWord)
        {
            SQLiteConnection con = null;
            SQLiteCommand cmd = null;
            User u = null;

            try
            {
                con = new SQLiteConnection("Data Source=" + location);
                cmd = new SQLiteCommand("select * from User where User_Name = @UN and Password = @PW and Active = 1", con);
                cmd.Parameters.Add("@UN", DbType.String);
                cmd.Parameters.Add("@PW", DbType.Int32);

                cmd.Parameters["@UN"].Value = uName;
                cmd.Parameters["@PW"].Value = pWord;

                con.Open();
                SQLiteDataReader dr = cmd.ExecuteReader();

                while (dr.Read())
                {
                    u = new User(Convert.ToString(dr["User_Name"]), Convert.ToString(dr["First_Name"]), Convert.ToString(dr["Last_Name"]), Convert.ToBoolean(dr["Admin"]));
                }

                return u;
            }
            catch (Exception e)
            {
                Console.WriteLine("Error: {0}", e.Message);
                return null;
            }
            finally
            {
            }
        }

        public bool verifyUser(string uName)
        {
            return false;
        }

        public void createUser(User u, int pWord)
        {
        }

        public void deleteUser(string uName)
        {
        }
        #endregion

        #region Courier Methods
        #endregion
    }
}
