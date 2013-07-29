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
        SQLiteConnection con;
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

        #region Database Connection
        private void connect()
        {
            con = new SQLiteConnection("Data Source=" + location);
            con.Open();
        }
        private void disconnect()
        {
            try
            {
                con.Close();
            }
            catch (SQLiteException e)
            {
                Console.Write("Error {0}", e.Message);
            }
            finally
            {
            }
        }
        #endregion

        public DataSet getPackages(bool allStops)
        {
            return null;
        }

        #region User Interaction
        public User login(string uName, int pWord)
        {
            SQLiteCommand cmd = null;
            User u = null;
            connect();
            try
            {
                cmd = new SQLiteCommand("select * from User where User_Name = @UN and Password = @PW and Active = 1", con);
                cmd.Parameters.Add("@UN", DbType.String);
                cmd.Parameters.Add("@PW", DbType.Int32);

                cmd.Parameters["@UN"].Value = uName;
                cmd.Parameters["@PW"].Value = pWord;

                SQLiteDataReader dr = cmd.ExecuteReader();

                while (dr.Read())
                {
                    u = new User(Convert.ToString(dr["User_Name"]), Convert.ToString(dr["First_Name"]), Convert.ToString(dr["Last_Name"]), Convert.ToBoolean(dr["Admin"]));
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Error: {0}", e.Message);
                u = null;
            }
            finally
            {
                disconnect();
            }
            return u;
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
