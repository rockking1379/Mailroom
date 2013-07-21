using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Common
{
    class Package : Object
    {
        #region Variables
        private string firstName { get; set; }
        private string lastName { get; set; }
        private string email { get; set; }
        private string date { get; set; }
        private string boxNumber { get; set; }
        private string stop { get; set; }
        private string trackingNumber { get; set; }
        private string user { get; set; }
        private string courier { get; set; }
        private bool delivered { get; set; }
        private bool pickedUp { get; set; }
        private bool returned { get; set; }
        private string pickUpDate { get; set; }
        #endregion

        #region Constructors
        /// <summary>
        /// Constructs a Package Object
        /// </summary>
        /// <param name="firstName">First Name on Package</param>
        /// <param name="lastName">Last Name on Package</param>
        /// <param name="email">Email Address associated with person on Package</param>
        /// <param name="date">Date package was scanned in</param>
        /// <param name="boxNumber">Box Number or Suite Number of Person receiving Package</param>
        /// <param name="stop">Stop package is going to</param>
        /// <param name="trackingNumber">Tracking Number on Package</param>
        /// <param name="user">Username of the Person who checked the Package in</param>
        /// <param name="courier">Courier who delivered the Package</param>
        /// <param name="delivered">If delivered to Stop</param>
        /// <param name="pickedUp">If Picked Up by intended Person</param>
        public Package(string firstName, string lastName, string email, string date, 
            string boxNumber, string stop, string trackingNumber, string user, string courier, 
            bool delivered, bool pickedUp)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.date = date;
            this.boxNumber = boxNumber;
            this.stop = stop;
            this.trackingNumber = trackingNumber;
            this.user = user;
            this.courier = courier;
            this.delivered = delivered;
            this.pickedUp = pickedUp;
            this.returned = false;
            this.pickUpDate = null;
        }
        /// <summary>
        /// Constructs a Package Object
        /// </summary>
        /// <param name="firstName">First Name on Package</param>
        /// <param name="lastName">Last Name on Package</param>
        /// <param name="email">Email Address associated with person on Package</param>
        /// <param name="date">Date package was scanned in</param>
        /// <param name="boxNumber">Box Number or Suite Number of Person receiving Package</param>
        /// <param name="stop">Stop package is going to</param>
        /// <param name="trackingNumber">Tracking Number on Package</param>
        /// <param name="user">Username of the Person who checked the Package in</param>
        /// <param name="courier">Courier who delivered the Package</param>
        /// <param name="delivered">If delivered to Stop</param>
        /// <param name="pickedUp">If Picked Up by intended Person</param>
        /// <param name="returned">Returned Status of Package(Default is False)</param>
        /// <param name="pickUpDate">Date Package was Picked Up(Default is Null)</param>
        public Package(string firstName, string lastName, string email, string date, 
            string boxNumber, string stop, string trackingNumber, string user, string courier, 
            bool delivered, bool pickedUp, bool returned, string pickUpDate)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.date = date;
            this.boxNumber = boxNumber;
            this.stop = stop;
            this.trackingNumber = trackingNumber;
            this.user = user;
            this.courier = courier;
            this.delivered = delivered;
            this.pickedUp = pickedUp;
            this.returned = returned;
            this.pickUpDate = pickUpDate;
        }
        #endregion
    }
}
