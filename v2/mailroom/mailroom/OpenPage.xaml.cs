#region Statements
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Data;
using Common;
#endregion

namespace mailroom
{
    /// <summary>
    /// Interaction logic for OpenPage.xaml
    /// </summary>
    public partial class OpenPage : Page
    {
        #region Variables
        MainWindow mWindow;
        DatabaseManager dbm;
        User cUser;
#endregion

        #region Constructor
        public OpenPage(MainWindow mWindow, DatabaseManager dbm, User u)
        {
            InitializeComponent();
            this.mWindow = mWindow;
            this.dbm = dbm;
            this.cUser = u;
            UserLabel.Content = "Welcome: " + cUser.firstName + " " + cUser.lastName;

#if DEBUG
            LblVersion.Visibility = System.Windows.Visibility.Visible;
#endif
            getPackages();
        }
        #endregion

        #region Logout
        /// <summary>
        /// Performed when Logout Button is Clicked
        /// </summary>
        /// <param name="sender">Sender of Event</param>
        /// <param name="e">Event Arguments</param>
        private void BtnLogout_Click(object sender, RoutedEventArgs e)
        {
            mWindow.ViewFrame.Navigate(new Login(mWindow));
        }
        #endregion

        #region Database
        void getPackages()
        {
            DataSet dSet = dbm.getPackages(true);
        }
        #endregion

        #region Quit
        /// <summary>
        /// Performed when Quit Button is Clicked
        /// </summary>
        /// <param name="sender">Sender of Event</param>
        /// <param name="e">Event Arguments</param>
        private void BtnQuit_Click(object sender, RoutedEventArgs e)
        {
            mWindow.Close();
        }
        #endregion
    }
}
