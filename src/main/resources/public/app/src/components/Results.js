  
import Item from './Item.js'
import { v4 as uuidv4 } from 'uuid';
import './Results.css'

function Results(props) {

  return (
    <div className="results">
      {props.users.map((user) => (
        <Item id={uuidv4()} data={user} key={uuidv4()}/>
			))}
    </div>
  )
}

export default Results