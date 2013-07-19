using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SQLite;

namespace Common
{
    public class DatabaseManager : Object
    {
        public DatabaseManager()
        {
            //Eh
        }

        #region User Interaction
        public bool login(string uName, int pWord)
        {
            if(uName == "sitzja" && pWord == 4096)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        #endregion
    }
}
