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
    /// Interaction logic for Login.xaml
    /// </summary>
    public partial class Login : Page
    {
        MainWindow mWindow;
        DatabaseManager dbm;

        public Login(MainWindow mWindow)
        {
            InitializeComponent();

            this.mWindow = mWindow;
            dbm = new DatabaseManager();
        }

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

        private void BtnLogin_Click(object sender, RoutedEventArgs e)
        {
            bool success = dbm.login(username.Text, 4096);

            if (!success)
            {
                ErrorLbl.Visibility = System.Windows.Visibility.Visible;
            }
            else
            {
                mWindow.ViewFrame.Navigate(new OpenPage(mWindow, dbm));
            }
        }

        private void BtnQuit_Click(object sender, RoutedEventArgs e)
        {
            mWindow.Close();
        }
    }
}
