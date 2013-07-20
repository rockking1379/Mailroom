using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Common
{
    public class User : Object
    {
        #region Variables
        private string username { get;set; }
        private string firstName { get; set; }
        private string lastName { get; set; }
        private bool admin { get; set; }
        #endregion

        #region Constructors
        /// <summary>
        /// Constructs new User
        /// </summary>
        /// <param name="username">Username of User</param>
        /// <param name="firstName">First Name of User</param>
        /// <param name="lastName">Last Name of User</param>
        public User(string username, string firstName, string lastName)
        {
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            admin = false;
        }
        /// <summary>
        /// Constructs new User
        /// </summary>
        /// <param name="username">Username of User</param>
        /// <param name="firstName">First Name of User</param>
        /// <param name="lastName">Last Name of User</param>
        /// <param name="admin">Administrative status of User</param>
        public User(string username, string firstName, string lastName, bool admin)
        {
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.admin = admin;
        }
        #endregion
    }
}
