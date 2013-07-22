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
using Common;
#endregion

namespace mailroom
{
    /// <summary>
    /// Interaction logic for Login.xaml
    /// </summary>
    public partial class Login : Page
    {
        #region Variables
        MainWindow mWindow;
        DatabaseManager dbm;
        #endregion

        #region Constructor
        public Login(MainWindow mWindow)
        {
            InitializeComponent();

            this.mWindow = mWindow;
            dbm = new DatabaseManager();
        }
        #endregion

        #region Keyboard Processing
        private void LoginPage_KeyDown(object sender, KeyEventArgs e)
        {
            switch (e.Key)
            {
                case Key.Escape:
                    {
                        BtnQuit_Click(null, null);
                        break;
                    }
                case Key.Tab:
                    {
                        if (username.Text == "Username")
                        {
                            username.Text = "";
                            break;
                        }
                        else
                        {
                            if (password.Password == "Password")
                            {
                                password.Password = "";
                                break;
                            }
                        }
                        break;
                    }
                case Key.Enter:
                    {
                        BtnLogin_Click(null, null);
                        break;
                    }
            }
        }
        #endregion

        #region Mouse Logic
        private void LoginPage_MouseEnter(object sender, MouseEventArgs e)
        {
            username_MouseEnter(null, null);
        }

        private void username_MouseEnter(object sender, MouseEventArgs e)
        {
            username.Focus();
            if (username.Text == "Username")
            {
                username.Text = "";
            }
        }
        #endregion

        #region Login
        private void BtnLogin_Click(object sender, RoutedEventArgs e)
        {
            User u = dbm.login(username.Text, password.Password.GetHashCode());

            if (u == null)
            {
                ErrorLbl.Visibility = System.Windows.Visibility.Visible;
            }
            else
            {
                mWindow.ViewFrame.Navigate(new OpenPage(mWindow, dbm, u));
            }
        }
        #endregion

        #region Quit
        private void BtnQuit_Click(object sender, RoutedEventArgs e)
        {
            mWindow.Close();
        }
        #endregion
    }
}
