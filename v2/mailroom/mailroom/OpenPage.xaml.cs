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
using Common;

namespace mailroom
{
    /// <summary>
    /// Interaction logic for OpenPage.xaml
    /// </summary>
    public partial class OpenPage : Page
    {
        MainWindow mWindow;
        DatabaseManager dbm;
        User cUser;

        public OpenPage(MainWindow mWindow, DatabaseManager dbm, User u)
        {
            InitializeComponent();
            this.mWindow = mWindow;
            this.dbm = dbm;
            this.cUser = u;
            UserLabel.Content = "Welcome: " + cUser.firstName + " " + cUser.lastName;
        }

        private void BtnLogout_Click(object sender, RoutedEventArgs e)
        {
            mWindow.ViewFrame.Navigate(new Login(mWindow));
        }

        private void BtnQuit_Click(object sender, RoutedEventArgs e)
        {
            mWindow.Close();
        }
    }
}
