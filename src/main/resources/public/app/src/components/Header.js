import './Header.css'
import { Link } from 'react-router-dom';

function Header() {
  
  return (
    <div className="header">
      <h1>
        Friends <span role="img" aria-label="Unicorn Emoji">🦄</span>
      </h1>
      <p>
        A simple tool for friends interesting topics.
      </p>
      <Link className="item-link" to="/" >Home</Link>
    </div>
  )
}

export default Header